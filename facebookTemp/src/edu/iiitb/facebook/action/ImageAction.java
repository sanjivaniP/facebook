package edu.iiitb.facebook.action;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.facebook.model.FacebookDAO;

public class ImageAction extends ActionSupport implements SessionAware {

  private int photoId;

  public int getPhotoId() {
	return photoId;
}
public void setPhotoId(int photoId) {
	this.photoId = photoId;
}
private Map<String, Object> session;

  public String execute() throws  IOException {
	  
	HttpServletResponse response = ServletActionContext.getResponse();
	response.setContentType("image/jpeg");
	InputStream in=FacebookDAO.getPhoto(getPhotoId());
    OutputStream out = response.getOutputStream();
    byte[] buffer = new byte[1024];
    int len;
    while ((len = in.read(buffer)) != -1) {
      out.write(buffer, 0, len);
    }
    
    return NONE;
  }
  @Override
  public void setSession(Map<String, Object> session) {
    this.session = session;

  }
}