package com.test.pdf;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Set;

import org.apache.pdfbox.contentstream.PDContentStream;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.pdmodel.common.filespecification.PDFileSpecification;
import org.apache.xmpbox.XMPMetadata;




public class Reader {
	private PDDocument document;
	
	
	/**
	 * 加载PDF
	 * @param pdfFile
	 * @throws IOException
	 */
	public void loadPDF(String pdfFile) throws IOException{
		document = PDDocument.load(new File(pdfFile));
	}
	
	/**
	 * 获取文件标题
	 * @return
	 */
	public String getTitle(){
		PDDocumentInformation information = document.getDocumentInformation();
		return information.getTitle();
	}
	/**
	 * 读取页面内容
	 * @return
	 * @throws IOException 
	 */
	public String readPageContents(int page) throws IOException{
		//TODO 
		PDPage pdpage = document.getPage(page);
		InputStream inputStream = pdpage.getContents();
		int len = inputStream.available();
		System.out.println("长度:"+len);
		/*
		Iterator<PDStream> streams = pdpage.getContentStreams();
		while (streams.hasNext()) {
			PDStream stream = streams.next();
			System.out.println(stream.getLength());
			PDFileSpecification fileSpecification = stream.getFile();
			System.out.println("");
		}
		*/
		return "";
	}
	
	public XMPMetadata getXMPMetadata(){
		return null;
	}
	
	public static void main(String[] args) throws InterruptedException {
		Reader reader = new Reader();
		try {
			reader.loadPDF("/Users/chenmingzhou/cmz/ebook/Manning.Netty.in.Action.2015.12.pdf");
			System.out.println(reader.getTitle());
			reader.readPageContents(201);

			
			//COSDocument cosDocument = reader.document.getDocument();
			
			
//			Set<String> sets = information.getMetadataKeys();
//			for(String key:sets){
//				System.out.println(key);
//			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
