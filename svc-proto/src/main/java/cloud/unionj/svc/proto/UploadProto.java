package cloud.unionj.svc.proto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.*;
import cloud.unionj.svc.vo.*;

public interface UploadProto {

    @PostMapping("/upload")
    ResponseEntity<byte[]> postUpload(
        @RequestPart(value="file", required=false) MultipartFile file
    );

}
