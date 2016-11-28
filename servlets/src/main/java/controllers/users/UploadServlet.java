//package controllers.users;
//
//import org.apache.commons.fileupload.FileItem;
//import org.apache.commons.fileupload.FileUploadException;
//import org.apache.commons.fileupload.disk.DiskFileItemFactory;
//import org.apache.commons.fileupload.servlet.ServletFileUpload;
//
//import javax.servlet.ServletContext;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.File;
//import java.io.IOException;
//import java.util.List;
//
////@WebServlet("/UploadPhoto")
//public class UploadServlet extends HttpServlet {
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
//
//        DiskFileItemFactory factory = new DiskFileItemFactory();
//
//        ServletContext servletContext = this.getServletConfig().getServletContext();
//        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
//        factory.setRepository(repository);
//
//        // here it fails
//        ServletFileUpload upload = new ServletFileUpload(factory);
//
//        try {
//            List<FileItem> items = upload.parseRequest(request);
//        } catch (FileUploadException e) {
//            e.printStackTrace();
//        }
//    }
//}
