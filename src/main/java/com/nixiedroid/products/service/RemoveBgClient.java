package com.nixiedroid.products.service;

import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestPart;

import java.io.File;

/**
 * Example of Request:<br><br>
 * Socket[addr=/127.0.0.1,port=56494,localport=8088]<br>
 * POST /removebg HTTP/1.1 <br>
 * Content-Type: multipart/form-data; charset=UTF-8; boundary=190b70c3d77<br>
 * X-Api-Key: TOKEN=)<br>
 * Accept: * /*<br>
 * User-Agent: Java/17.0.2<br>
 * Host: localhost:8088<br>
 * Connection: keep-alive<br>
 * Content-Length: 310<br>
 * <br>
 * --190b70c3d77<br>
 * Content-Disposition: form-data; name="image_file"; filename="char2.png"<br>
 * Content-Type: image/png<br>
 * Content-Transfer-Encoding: binary<br>
 * <br>
 * JFIF_FILE_CONTENTS_STREAM_DATA12<br>
 * --190b70c3d77<br>
 * Content-Disposition: form-data; name="size"<br>
 * Content-Type: text/plain; charset=UTF-8<br>
 */
@FeignClient(name = "removeBg", url = "https://api.remove.bg/v1.0/removebg")
public interface RemoveBgClient {

    @PostMapping(value = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    byte[] removeBackground(
            @RequestPart("image_file") File file,
            @RequestPart("size") String size,
            @RequestHeader("X-Api-Key") String apiKey
    );

}
