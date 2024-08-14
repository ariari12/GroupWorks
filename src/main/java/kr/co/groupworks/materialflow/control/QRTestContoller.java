package kr.co.groupworks.materialflow.control;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import io.swagger.v3.oas.annotations.Hidden;
import kr.co.groupworks.materialflow.dto.MaterialItemVO;
import kr.co.groupworks.materialflow.entity.ItemStatus;
import kr.co.groupworks.materialflow.service.MaterialService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Base64;
import java.util.List;

@Slf4j
@Hidden
@Controller
@RequestMapping(value = "/materialflow/qr")
@RequiredArgsConstructor
public class QRTestContoller {
    static final int HEIGHT = 460, WIDTH = 460;
    private final MaterialService materialService;

    @GetMapping(value = "/qr-code/{bomId}")
    public String materialStatQRcode(@PathVariable("bomId") long bomId, Model model) {
        List<MaterialItemVO> itemList = materialService.getItemList(bomId);
        if(itemList == null || itemList.isEmpty()) return "/error/404";

        model.addAttribute("codeList", itemList.stream().map(i -> {
            try {
                ItemStatus status = ItemStatus.getItemStatus(i.getItemStatus());
                // QR Code 생성 정보를 가지는 객체 생성
                // QR code 로 이동할 url
                String url = "http://" + InetAddress.getLocalHost().getHostAddress()
                        + "/materialflow/qr/receive/" + i.getId() + "/" + status.ordinal();
                log.info("url: {}", url);

                // QR Code - Image String List 요소 반환
                return setQrInfo(url, status);
            } catch (IOException | WriterException e) { throw new RuntimeException(e); }
        }).toList());
        model.addAttribute("itemList", itemList);
        return "materialflow/qrcode/qrPrintPage";
    }

    @GetMapping(value = "/receive/{itemId}/{statusCode}")
    public String qrReceive(@PathVariable Long itemId, @PathVariable int statusCode, Model model) {
        if(ItemStatus.checkedItemStatus(statusCode)) {
            materialService.updateItem(itemId, statusCode +1).forEach(model::addAttribute);
            return "materialflow/qrcode/qrReceivePage";
        }
        return "materialflow/qrcode/notQrReceivePage";
    }

    private String setQrInfo(String url, ItemStatus itemStatus) throws WriterException, IOException {
        // QR code 색상지정
        MatrixToImageConfig matrixImageConfig = switch (itemStatus) {
            case RECEIVING ->  new MatrixToImageConfig(MatrixToImageConfig.BLACK, 0xfff6eb17);
            case SHIPPING -> new MatrixToImageConfig(MatrixToImageConfig.BLACK, 0xff011ce9);
            default -> new MatrixToImageConfig(MatrixToImageConfig.BLACK, 0xFFBDBDBD);
        };

        // BitMatrix: qr code 정보 생성
        BitMatrix encode = new MultiFormatWriter().encode(url, BarcodeFormat.QR_CODE, QRTestContoller.WIDTH, QRTestContoller.HEIGHT);
        // QR Code - Image 생성.
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(encode, "PNG", byteArrayOutputStream, matrixImageConfig);
        // String QR Image 반환
        return Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
    }
}
