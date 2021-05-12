package cloud.unionj.svc.proto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.*;
import cloud.unionj.svc.vo.*;

public interface TsProto {

    @PostMapping("/ts/upload")
    ResponseEntity<byte[]> postTsUpload(
        @RequestPart(value="file", required=false) MultipartFile file
    );

    @GetMapping("/ts/url")
    ResponseEntity<byte[]> getTsUrl(
        @RequestParam("url") String url
    );

}
