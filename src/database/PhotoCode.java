//ͼƬ���䣬ѡ��ͼƬ������ͼƬ����Ŀ���ļ����£�����photo

package database;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.JFileChooser;


//����ѡ���ļ����ϴ�ͼƬ��
public class PhotoCode extends javax.swing.filechooser.FileFilter{
	public static void main(String[] args) {
		 PhotoCode fileFilter=new PhotoCode();
		 JFileChooser fileChooser = new JFileChooser();
		 fileChooser.setFileFilter(fileFilter);
		 fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		 int returnVal = fileChooser.showOpenDialog(fileChooser);
		 if(returnVal == JFileChooser.APPROVE_OPTION){ 
			 String filePath = null;
			try {
				filePath = fileChooser.getSelectedFile().getCanonicalPath();
			} catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}//���������ѡ����ļ��е�·��
			 System.out.println(filePath);//������ѡ�ļ���·��
		 }
		 File resfile = new File(System.getProperty("user.dir")+"/src/images/"+"ccc.jpg");
		 try {
			//copyFileUsingFileStreams(fileChooser.getSelectedFile(),resfile);
			System.out.println("�ɹ�"+resfile.getCanonicalPath());//������ѡ�ļ���·��
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}

	@Override
	public boolean accept(java.io.File f) {
		if (f.isDirectory())return true;
		return f.getName().endsWith(".jpg");
	}

	@Override
	public String getDescription() {
		return ".jpg";
	}
	private static void PhotoCode() {
	 PhotoCode fileFilter=new PhotoCode();
	 JFileChooser fileChooser = new JFileChooser();
	 fileChooser.setFileFilter(fileFilter);
	 fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
	 int returnVal = fileChooser.showOpenDialog(fileChooser);
	 if(returnVal == JFileChooser.APPROVE_OPTION){ 
		 String filePath= fileChooser.getSelectedFile().getAbsolutePath();//���������ѡ����ļ��е�·��
		 System.out.println(filePath);//������ѡ�ļ���·��
	 }
	}

	private static void copyFileUsingFileStreams(File source, File dest)
			throws IOException {
			InputStream input = null;
			OutputStream output = null;
			try {
			input = new FileInputStream(source);
			output = new FileOutputStream(dest);
			byte[] buf = new byte[1024];
			int bytesRead;
			while ((bytesRead = input.read(buf)) > 0) {
			output.write(buf, 0, bytesRead);
			}
			} finally {
			input.close();
			output.close();
			}
			}

}
