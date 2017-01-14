package edu.iiitb.facebook.action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import edu.iiitb.facebook.model.FacebookDAO;
import edu.iiitb.facebook.model.NewsFeedDao;
import edu.iiitb.facebook.model.PhotoAlbum;
import edu.iiitb.facebook.model.picDAO;

public class picAction {

	String isHyperlink;
	String areFriends;
	int profileid;
	String firstName;
	String lastName;
	picAction pic;
	
	
	public picAction getPic() {
		return pic;
	}

	public void setPic(picAction pic) {
		this.pic = pic;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getProfileid() {
		return profileid;
	}

	public void setProfileid(int profileid) {
		this.profileid = profileid;
	}

	public String getAreFriends() {
		return areFriends;
	}

	public void setAreFriends(String areFriends) {
		this.areFriends = areFriends;
	}

	public String getIsHyperlink() {
		return isHyperlink;
	}

	public void setIsHyperlink(String isHyperlink) {
		this.isHyperlink = isHyperlink;
	}


	/*-----------------------UPLOAD--------------------
	// File file_1;
	File files_list;
	private List<File> file_1 = new ArrayList<File>();

	public File getFiles_list() {
		return files_list;
	}

	public void setFiles_list(File files_list) {
		this.files_list = files_list;
	}

	public List<File> getFile_1() {
		return file_1;
	}

	public void setFile_1(List<File> file_1) {
		this.file_1 = file_1;
	}

	
	 * public File getFile_1() { return file_1; }
	 * 
	 * public void setFile_1(File file_1) { this.file_1 = file_1; }
	 
	
	 * public String uploadPhoto() {
	 * System.out.println("MMMMMMMMMMMMMMMMMM=="+files_list); int
	 * i=file_1.size(); System.out.println("MMMMMMMMMMMMMMMMMM=="+i); return
	 * "success"; }
	 

	-----------------------UPLOAD--------------------

	------------------------------------- UPLOAD PHOTO START-----------------------------------*/
	/*
	 * private List<File> upload = new ArrayList<File>(); private List<String>
	 * uploadFileNames = new ArrayList<String>(); private List<String>
	 * uploadContentTypes = new ArrayList<String>(); public List<File>
	 * getUpload() { return upload; }
	 * 
	 * public void setUploads(List<File> upload) { this.upload = upload; }
	 * 
	 * public List<String> getUploadFileNames() { return uploadFileNames; }
	 * 
	 * public void setUploadFileNames(List<String> uploadFileNames) {
	 * this.uploadFileNames = uploadFileNames; }
	 * 
	 * public List<String> getUploadContentTypes() { return uploadContentTypes;
	 * }
	 * 
	 * public void setUploadContentTypes(List<String> uploadContentTypes) {
	 * this.uploadContentTypes = uploadContentTypes; }
	 */
	private File upload1;
	private File upload2;
	private File upload3;
	private File upload4;
	private File upload5;

	private String upload1FileNames;
	private String upload2FileNames;
	private String upload3FileNames;
	private String upload4FileNames;
	private String upload5FileNames;

	private String upload1ContentTypes;
	private String upload2ContentTypes;
	private String upload3ContentTypes;
	private String upload4ContentTypes;
	private String upload5ContentTypes;

	private String caption1;
	private String caption2;
	private String caption3;
	private String caption4;
	private String caption5;

	private String location1;
	private String location2;
	private String location3;
	private String location4;
	private String location5;

	public String getCaption1() {
		return caption1;
	}

	public void setCaption1(String caption1) {
		this.caption1 = caption1;
	}

	public String getCaption2() {
		return caption2;
	}

	public void setCaption2(String caption2) {
		this.caption2 = caption2;
	}

	public String getCaption3() {
		return caption3;
	}

	public void setCaption3(String caption3) {
		this.caption3 = caption3;
	}

	public String getCaption4() {
		return caption4;
	}

	public void setCaption4(String caption4) {
		this.caption4 = caption4;
	}

	public String getCaption5() {
		return caption5;
	}

	public void setCaption5(String caption5) {
		this.caption5 = caption5;
	}

	public String getLocation1() {
		return location1;
	}

	public void setLocation1(String location1) {
		this.location1 = location1;
	}

	public String getLocation2() {
		return location2;
	}

	public void setLocation2(String location2) {
		this.location2 = location2;
	}

	public String getLocation3() {
		return location3;
	}

	public void setLocation3(String location3) {
		this.location3 = location3;
	}

	public String getLocation4() {
		return location4;
	}

	public void setLocation4(String location4) {
		this.location4 = location4;
	}

	public String getLocation5() {
		return location5;
	}

	public void setLocation5(String location5) {
		this.location5 = location5;
	}

	public File getUpload1() {
		return upload1;
	}

	public void setUpload1(File upload1) {
		this.upload1 = upload1;
	}

	public File getUpload2() {
		return upload2;
	}

	public void setUpload2(File upload2) {
		this.upload2 = upload2;
	}

	public File getUpload3() {
		return upload3;
	}

	public void setUpload3(File upload3) {
		this.upload3 = upload3;
	}

	public File getUpload4() {
		return upload4;
	}

	public void setUpload4(File upload4) {
		this.upload4 = upload4;
	}

	public File getUpload5() {
		return upload5;
	}

	public void setUpload5(File upload5) {
		this.upload5 = upload5;
	}

	public String getUpload1FileNames() {
		return upload1FileNames;
	}

	public void setUpload1FileNames(String upload1FileNames) {
		this.upload1FileNames = upload1FileNames;
	}

	public String getUpload2FileNames() {
		return upload2FileNames;
	}

	public void setUpload2FileNames(String upload2FileNames) {
		this.upload2FileNames = upload2FileNames;
	}

	public String getUpload3FileNames() {
		return upload3FileNames;
	}

	public void setUpload3FileNames(String upload3FileNames) {
		this.upload3FileNames = upload3FileNames;
	}

	public String getUpload4FileNames() {
		return upload4FileNames;
	}

	public void setUpload4FileNames(String upload4FileNames) {
		this.upload4FileNames = upload4FileNames;
	}

	public String getUpload5FileNames() {
		return upload5FileNames;
	}

	public void setUpload5FileNames(String upload5FileNames) {
		this.upload5FileNames = upload5FileNames;
	}

	public String getUpload1ContentTypes() {
		return upload1ContentTypes;
	}

	public void setUpload1ContentTypes(String upload1ContentTypes) {
		this.upload1ContentTypes = upload1ContentTypes;
	}

	public String getUpload2ContentTypes() {
		return upload2ContentTypes;
	}

	public void setUpload2ContentTypes(String upload2ContentTypes) {
		this.upload2ContentTypes = upload2ContentTypes;
	}

	public String getUpload3ContentTypes() {
		return upload3ContentTypes;
	}

	public void setUpload3ContentTypes(String upload3ContentTypes) {
		this.upload3ContentTypes = upload3ContentTypes;
	}

	public String getUpload4ContentTypes() {
		return upload4ContentTypes;
	}

	public void setUpload4ContentTypes(String upload4ContentTypes) {
		this.upload4ContentTypes = upload4ContentTypes;
	}

	public String getUpload5ContentTypes() {
		return upload5ContentTypes;
	}

	public void setUpload5ContentTypes(String upload5ContentTypes) {
		this.upload5ContentTypes = upload5ContentTypes;
	}

	/*
	 * public File getUpload() { return upload; }
	 * 
	 * public void setUpload(File upload) { this.upload = upload; }
	 * 
	 * public String getUploadFileNames() { return uploadFileNames; }
	 * 
	 * public void setUploadFileNames(String uploadFileNames) {
	 * this.uploadFileNames = uploadFileNames; }
	 * 
	 * public String getUploadContentTypes() { return uploadContentTypes; }
	 * 
	 * public void setUploadContentTypes(String uploadContentTypes) {
	 * this.uploadContentTypes = uploadContentTypes; }
	 */

	public String uploadPhoto() throws Exception {

		/*
		 * System.out.println("\n\n shubham   upload1");
		 * System.out.println("files:"); for (File u: upload) {
		 * System.out.println("*** "+u+"\t"+u.length()); }
		 * System.out.println("filenames:"); for (String n: uploadFileNames) {
		 * System.out.println("*** "+n); } System.out.println("content types:");
		 * for (String c: uploadContentTypes) { System.out.println("*** "+c); }
		 */System.out.println("\n\n");

		HttpSession session = ServletActionContext.getRequest().getSession(
				false);
		if (session == null || session.getAttribute("login") == null) {
			return "login";
		} else {
			// //System.out.println("inside else");
			int profileid = (Integer) session.getAttribute("profileId");

			System.out.println("SHUBHAM==" + upload1);
			System.out.println("SHUBHAM==" + upload2);
			System.out.println("SHUBHAM==" + upload3);
			System.out.println("SHUBHAM==" + upload4);
			System.out.println("SHUBHAM==" + upload5);
			
			if (upload1 != null) {
				FacebookDAO.insertPhotoUpload(upload1, profileid, caption1,
						location1);
			}
			if (upload2 != null) {
				FacebookDAO.insertPhotoUpload(upload2, profileid, caption2,
						location2);
			}
			if (upload3 != null) {
				FacebookDAO.insertPhotoUpload(upload3, profileid, caption3,
						location3);
			}
			if (upload4 != null) {
				FacebookDAO.insertPhotoUpload(upload4, profileid, caption4,
						location4);
			}
			if (upload5 != null) {
				FacebookDAO.insertPhotoUpload(upload5, profileid, caption5,
						location5);
			}
		}
		return "success";
	}

	/*------------------------------------- UPLOAD PHOTO END-----------------------------------*/

	
	/*--------------------------------------CREATE ALBUM START--------------------------*/
	
	
	String albumCaption;
	String albumLocation;
	
	public String getAlbumCaption() {
		return albumCaption;
	}

	public void setAlbumCaption(String albumCaption) {
		this.albumCaption = albumCaption;
	}

	public String getAlbumLocation() {
		return albumLocation;
	}

	public void setAlbumLocation(String albumLocation) {
		this.albumLocation = albumLocation;
	}
	
	public String uploadAlbum() throws Exception {

		/*
		 * System.out.println("\n\n shubham   upload1");
		 * System.out.println("files:"); for (File u: upload) {
		 * System.out.println("*** "+u+"\t"+u.length()); }
		 * System.out.println("filenames:"); for (String n: uploadFileNames) {
		 * System.out.println("*** "+n); } System.out.println("content types:");
		 * for (String c: uploadContentTypes) { System.out.println("*** "+c); }
		 */System.out.println("\n\n");

		HttpSession session = ServletActionContext.getRequest().getSession(
				false);
		if (session == null || session.getAttribute("login") == null) {
			return "login";
		} else {
			// //System.out.println("inside else");
			int profileid = (Integer) session.getAttribute("profileId");
			int albumId=0;
			System.out.println("SHUBHAM==" + upload1);
			System.out.println("SHUBHAM==" + upload2);
			System.out.println("SHUBHAM==" + upload3);
			System.out.println("SHUBHAM==" + upload4);
			System.out.println("SHUBHAM==" + upload5);
			if(upload1!=null ||upload2!=null||upload3!=null||upload4!=null||upload5!=null)
			{
				FacebookDAO.updateAlbum(albumCaption,albumLocation,profileid);
				 albumId=FacebookDAO.findMaxAlbumID();
				
			}
			
			if (upload1 != null) {
				FacebookDAO.insertAlbumUpload(upload1, profileid, caption1,
						location1,albumId);
			}
			if (upload2 != null) {
				FacebookDAO.insertAlbumUpload(upload2, profileid, caption2,
						location2,albumId);
			}
			if (upload3 != null) {
				FacebookDAO.insertAlbumUpload(upload3, profileid, caption3,
						location3,albumId);
			}
			if (upload4 != null) {
				FacebookDAO.insertAlbumUpload(upload4, profileid, caption4,
						location4,albumId);
			}
			if (upload5 != null) {
				FacebookDAO.insertAlbumUpload(upload5, profileid, caption5,
						location5,albumId);
			}
		}
		return "success";
	}
	/*--------------------------------------CREATE ALBUM START--------------------------*/
	
	
	

	int coverPicId;
	int profileId;
	private String myFileContentType;
	private String myFileFileName;
	private File myFile;
	private String location;
	private String caption;
	
	public String getMyFileContentType() {
		return myFileContentType;
	}

	public void setMyFileContentType(String myFileContentType) {
		this.myFileContentType = myFileContentType;
	}

	public String getMyFileFileName() {
		return myFileFileName;
	}

	public void setMyFileFileName(String myFileFileName) {
		this.myFileFileName = myFileFileName;
	}

	public File getMyFile() {
		return myFile;
	}

	public void setMyFile(File myFile) {
		this.myFile = myFile;
	}


	public String getLocation() {
		return location;
	}

	public int getProfileId() {
		return profileId;
	}

	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public int getCoverPicId() {
		return coverPicId;
	}

	public void setCoverPicId(int coverPicId) {
		this.coverPicId = coverPicId;
	}

	public String loadCoverPic() {
		HttpSession session = ServletActionContext.getRequest().getSession(
				false);
		if (session == null || session.getAttribute("login") == null) {
			return "login";
		} else {
			// //System.out.println("inside else");
			int profileid = (Integer) session.getAttribute("profileId");

			pic = picDAO.getCoverPic(profileid);
			
			return "success";
		}
	}

	public String editProfilePic() throws IOException {
		HttpSession session = ServletActionContext.getRequest().getSession(
				false);
		if (session == null || session.getAttribute("login") == null) {
			return "login";
		} else {
			// //System.out.println("inside else");
			int profileid = (Integer) session.getAttribute("profileId");

			if (myFile != null) {
	/*			String destPath = "/home/pawan/SHUBHAM/facebook_18_04/facebookTemp/WebContent/Images/";
				File destFile = new File(destPath, myFileFileName);

				FileUtils.copyFile(myFile, destFile);
*/
				FacebookDAO.insertProfileImage(myFile, profileid, caption,
						location);
				System.out.println("FILE==" + myFile);

			}

			/*
			 * coverPicId=picDAO.getCoverPic(profileid); if(coverPicId==0){
			 * //System.out.println("coverpicID before"+coverPicId);
			 * coverPicId=4;
			 * //System.out.println("coverpicID after"+coverPicId); }
			 */
			return "success";
		}
	}


	public String editCoverPic() throws IOException {
		HttpSession session = ServletActionContext.getRequest().getSession(
				false);
		if (session == null || session.getAttribute("login") == null) {
			return "login";
		} else {
			// //System.out.println("inside else");
			int profileid = (Integer) session.getAttribute("profileId");

			if (myFile != null) {
/*				String destPath = "/home/pawan/SHUBHAM/facebook_18_04/facebookTemp/WebContent/Images/";
				File destFile = new File(destPath, myFileFileName);

				FileUtils.copyFile(myFile, destFile);
*/
				FacebookDAO.insertCoverImage(myFile, profileid, caption,
						location);
				System.out.println("FILE==" + myFile);

			}

			/*
			 * coverPicId=picDAO.getCoverPic(profileid); if(coverPicId==0){
			 * //System.out.println("coverpicID before"+coverPicId);
			 * coverPicId=4;
			 * //System.out.println("coverpicID after"+coverPicId); }
			 */
			return "success";
		}
	}

	public String loadCoverPicForHyperlink(){
		HttpSession session = ServletActionContext.getRequest().getSession(
				false);
		if (session == null || session.getAttribute("login") == null) {
			return "login";
		} else {
			// //System.out.println("inside else");
		 profileid = (Integer) session.getAttribute("profileId");
		setIsHyperlink("true");
		areFriends=picDAO.areFriends(profileid, profileId);
		pic=picDAO.getCoverPic(profileId);
		
		return "success";
		}
	}
private ArrayList<Integer> photoIdList=new ArrayList<Integer>();
private ArrayList<PhotoAlbum> photoAlbum=new ArrayList<PhotoAlbum>();
	public String viewPhotos() {
		HttpSession session = ServletActionContext.getRequest().getSession(
				false);
		if (session == null || session.getAttribute("login") == null) {
			return "login";
		} else {
			// //System.out.println("inside else");
			profileId = (Integer) session.getAttribute("profileId");
			setPhotoAlbum(picDAO.getPhotoAlbum(profileId));
			setPhotoIdList(picDAO.getPics(profileId));
			System.out.println("Pic retrieval on about"+photoIdList);
			return "success";
		}
	}

	public ArrayList<Integer> getPhotoIdList() {
		return photoIdList;
	}

	public void setPhotoIdList(ArrayList<Integer> photoIdList) {
		this.photoIdList = photoIdList;
	}

	public ArrayList<PhotoAlbum> getPhotoAlbum() {
		return photoAlbum;
	}

	public void setPhotoAlbum(ArrayList<PhotoAlbum> photoAlbum) {
		this.photoAlbum = photoAlbum;
	}

	
}
