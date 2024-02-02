package com.pay.test;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

public class ReadImage {
	public static void save(String url) {
		BufferedImage img= null;
		HttpURLConnection conn = null;
		try {
			URL imgUrl = new URL(url);
			conn = (HttpURLConnection) imgUrl.openConnection();
			conn.setRequestProperty("Referer", url);

			img = ImageIO.read(conn.getInputStream());
//			FileOutputStream fos =
//					new FileOutputStream("C:\\intellij workspace\\test\\src\\main\\resources\\upload\\images");
//			ImageIO.write(img, "png", fos);
//			fos.close();

			// 파일 이름을 지정합니다. 예시로 현재 시간을 기반으로 파일 이름을 생성합니다.
			String fileName = UUID.randomUUID().toString() + ".png";

			// 파일이 저장될 디렉토리
			String directoryPath = "C:\\intellij workspace\\test\\src\\main\\resources\\upload\\images";

			// 파일 전체 경로
			String filePath = directoryPath + File.separator + fileName;

			// 디렉토리가 존재하지 않으면 생성
			File directory = new File(directoryPath);
			if (!directory.exists()) {
				directory.mkdirs();
			}

			FileOutputStream fos = new FileOutputStream(filePath);
			ImageIO.write(img, "png", fos);
			fos.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
