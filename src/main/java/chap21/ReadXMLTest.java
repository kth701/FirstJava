package chap21;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ReadXMLTest {

	public static void main(String[] args) throws Exception {
		
		File fXmlFile = new File("src\\main\\java\\chap21\\company.xml");
		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		doc.getDocumentElement().normalize();
		
		System.out.println("Root element:"+doc.getDocumentElement().getNodeName());
		
		NodeList nList = doc.getElementsByTagName("emp");
		System.out.println("----");
		for (int i=0; i<nList.getLength(); i++) {
			Node nNode = nList.item(i);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				
				System.out.println("firstName: "+getTagValue("firstname", eElement));
				System.out.println("lastName: "	+getTagValue("lastname", eElement));
				System.out.println("nickName: "	+getTagValue("nickname", eElement));
				System.out.println("salary: "	+getTagValue("salary", eElement));
				
				System.out.println();
			}
		}

	}
	
	private static String getTagValue(String sTag, Element eElement) {
		NodeList nList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
		Node nValue = (Node) nList.item(0);

		return nValue.getNodeValue();
	}

}

/*
XML(eXtensible Makeup Languag): 확장성 있는 마크업 언어
프로그램 간에 정보를 교환하기 위한 데이터 형식

구서용요소
태그(tag) : 데이터의 구성
속성(attribute): 데이터를 상세하게 설명하는 용도

태그 규칙
- 모든 태그는 시작태그와 종료 태그를 가져야한다.
태그는 또 다른 하위 태그를 가질수 있다.(계층 구조)

형식) <사용자가 정의 태그이름>담겨질 내용</사용자가 정의 태그이름>
	<?xml version="1.0" encoding="UTF-8" ?>
	<info>
		<name>홍길동</name>
		<address>부산</address>
		요소안에 이스케이프 문자가 있을 경우
		<other>
			<![CDATA[
				SELECT * 
				FROM DUAL
				WHERE a < b
				AND b > c
			]]>
		</other>
	</info>

*/