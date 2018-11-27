package yrzutils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.log4j.Logger;
import org.junit.Test;

 
 
 

/**
 * 常用文件处理工具类
 * @author yrz
 *
 */
public class FileUtils {
	
	 public static final String SEPARATOR = java.io.File.separator;
	private static final Logger LOGGER = Logger.getLogger(FileUtils.class);
    
    /**
     * <P>根据指定的路径创建目录，指定的路径必须是绝对路径</P>
     * @param folderPhysicalPath
     * @return void
     * @throws
     * @author yrz
     * @version V1.0
     */
    public static void createFolder(String folderPhysicalPath) {
    	File folder = new File(folderPhysicalPath);
    	try {
    	    if(!folder.exists()) {
    		if(folder.mkdirs()){
    			LOGGER.debug("目录："+ folderPhysicalPath + "不存在，创建之！");
    		}
    		else{
    			LOGGER.error("目录："+ folderPhysicalPath + "不存在，创建之！但创建出错！");
    		}
    	    } else {
    	    	LOGGER.debug("目录："+ folderPhysicalPath + "已存在，不必创建！");
    	    }		
    	} catch(Exception e) {
    	    e.printStackTrace();
    	    LOGGER.error("根据指定的路径创建目录的操作失败，需要创建的目录：" + folderPhysicalPath);
    	}
    }
    
    /**
     * 根据指定的文件路径获取到该文件所在的目录，指定的文件路径应该是绝对路径
     * @param filePhysicalPath
     * @return String
     * @throws
     * @author yrz
     * @version V1.0
     */
    public static String getFolderPhysicalPath(String filePhysicalPath) {
    	filePhysicalPath = filePhysicalPath.trim();
    	if(filePhysicalPath.equals("")){
    	    return "";
    	}
    	if(filePhysicalPath.lastIndexOf(SEPARATOR) < 0){
    	    return SEPARATOR;
    	}
    	String folderPhysicalPath = filePhysicalPath.substring(0,filePhysicalPath.lastIndexOf(SEPARATOR));
    	System.out.println("folderPhysicalPath:"+folderPhysicalPath);
    	if(folderPhysicalPath.equals("")){
    	    return SEPARATOR;
    	}
    	else
    	{
    	    return folderPhysicalPath;
    	}
    }

	/**
	 * 读取本地文件转换为字节数组
	 * @param fileName(文件路径)
	 * @return
	 * @author yrz
	 */
	public static byte[] readPdfFile(String fileName) {
		
		byte[] buffer = null;
		try {

			File file = new File(fileName);
			InputStream fis;

			FileInputStream fiws = new FileInputStream(file);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] b = new byte[1024];
			int n;
			while ((n = fiws.read(b)) != -1) {
				bos.write(b, 0, n);
			}
			fiws.close();
			bos.close();
			buffer = bos.toByteArray();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return buffer;

	}
	
 
	

}
