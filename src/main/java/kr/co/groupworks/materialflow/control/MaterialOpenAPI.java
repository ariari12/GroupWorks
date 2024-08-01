package kr.co.groupworks.materialflow.control;

import kr.co.groupworks.materialflow.service.MaterialOpenApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MaterialOpenAPI {
    /* Material Flow Management OpenAPI */
    MaterialOpenApiService materialOpenApiService;
}
