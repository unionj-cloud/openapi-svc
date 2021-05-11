package cloud.unionj.svc.server.controller;

import cloud.unionj.svc.proto.UploadProto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author created by wubin
 * @version v0.0.1
 * description: cloud.unionj.svc.server.controller
 * date:2021/5/11
 */
public class ProtoImpl implements UploadProto {
  @Override
  public ResponseEntity<byte[]> postUpload(MultipartFile file) {
    return null;
  }
}
