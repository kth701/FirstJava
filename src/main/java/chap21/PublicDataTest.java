package chap21;

import java.io.InputStream;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class PublicDataTest {
	


	public static void main(String[] args) throws Exception {
		// 서울시 문화재 공공데이터 API구현하기
		// key : 68516b446268706836355859677a62
		
		URL url = new URL("http://openapi.seoul.go.kr:8088/68516b446268706836355859677a62/xml/SebcHeritageInfoEng/1/5/");
		InputStream stream = url.openStream();
		
		char ch = 0;
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(stream);
		doc.getDocumentElement().normalize();
		
		NodeList nList = doc.getElementsByTagName("row");
		System.out.println("----");
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			
			
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {// 단일요소(노드) 판별
				
				// XML태그를 이용해서 각각의 문화재0 상세정보를 가져와서 콘솔화면에 출력
				Element eElement = (Element)nNode;
				
				System.out.println("----------------------------");
				System.out.println("문화재 키값: "+ getTageValue("MAIN_KEY", eElement));
				System.out.println("문화재 일련번호: "+ getTageValue("MNG_NO", eElement));
				System.out.println("문화재 명칭: "+ getTageValue("NAME_ENG", eElement));
				System.out.println("문화재 수량 규모: "+ getTageValue("QUANTITY_SCALE", eElement));
				
			}
		
		}

	}
	
	private static String getTageValue(String sTag, Element eElement) {
		NodeList nList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
		Node nValue = (Node) nList.item(0);
		
		if (nValue == null) return "1";// 문화재 수량이 없는 경우 "1"로 설정하영 반환
		return nValue.getNodeValue();
	}

}
