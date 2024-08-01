package kr.co.groupworks.materialflow.control;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
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

@Slf4j
@Controller
@RequestMapping(value = "/qr")
@RequiredArgsConstructor
public class QRTestContoller {

    @GetMapping("/test/{no}")
//    public ResponseEntity<byte[]> qrToTistory(@PathVariable int no, Model model) throws WriterException, IOException {
    public String qrToMove(@PathVariable int no, Model model) throws WriterException, IOException {
        // QR 정보
        int width = 460;
        int height = 460;
        String url = "http://" + InetAddress.getLocalHost().getHostAddress() + "/qr/move/" + no;
        log.info("qr/test - url: {}", url);

        // QR Code - BitMatrix: qr code 정보 생성
        BitMatrix encode = new MultiFormatWriter().encode(url, BarcodeFormat.QR_CODE, width, height);
        MatrixToImageConfig custom = new MatrixToImageConfig(MatrixToImageConfig.BLACK, -200);

        // QR Code - Image 생성. : 1회성으로 생성해야 하기 때문에
        // stream으로 Generate(1회성이 아니면 File로 작성 가능.)
        try {
            //output Stream
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            //Bitmatrix, file.format, outputStream
            MatrixToImageWriter.writeToStream(encode, "PNG", out, custom);

            model.addAttribute("qrCode", Base64.getEncoder().encodeToString(out.toByteArray()));
            return "materialflow/qrcode/qrCodePage";
//            return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(out.toByteArray());
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @GetMapping(value = "/move/{no}")
    public String qrMove(@PathVariable int no, Model model) {
        log.info("/qr/Move no: {}", no);
        model.addAttribute("no", no);

        return "materialflow/qrcode/qrPageMove";
    }
}
