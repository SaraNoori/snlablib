package edu.francistuttle;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

public class App 
{
    public static void main( String[] args ) throws Exception
    {
        Document document = readXMLFromFile("C:/Users/sn1044604/Desktop/Github/snlablib/src/main/java/edu/francistuttle/lab.xml");

        Element root = document.getDocumentElement();
        System.out.println(root.getNodeName());

        System.out.println("-----------------------");

        NodeList nList = document.getElementsByTagName("computer");

        for (int index = 0; index < nList.getLength(); index++)
        {
            Node node = nList.item(index);
            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
                Element elmnt = (Element) node;

                // print out all the stuffs in it
                System.out.println(elmnt.getTagName());
                System.out.println("\tManufacturer: " + elmnt.getElementsByTagName("manufacturer").item(0).getTextContent());
                System.out.println("\tYear: " + elmnt.getElementsByTagName("year").item(0).getTextContent());
                System.out.println("\tProcessor: " + elmnt.getElementsByTagName("processor").item(0).getTextContent());

                
                // get list of monitors
                NodeList monitorNodes = elmnt.getElementsByTagName("monitor");
                
                // loop through monitors
                for (int i = 0; i < monitorNodes.getLength(); i++)
                {
                    Node monitNode = monitorNodes.item(i);
                    if (monitNode.getNodeType() == Node.ELEMENT_NODE)
                    {
                        Element monitElement = (Element) monitNode;

                        // print out all the stuffs in monitor
                        System.out.println("\t" + monitElement.getTagName());
                        System.out.println("\t\tManufacturer: " + monitElement.getElementsByTagName("manufacturer").item(0).getTextContent());
                        System.out.println("\t\tResolution: " + monitElement.getElementsByTagName("resolution").item(0).getTextContent());
                    }
                }
                
            }
        }


    }

    public static Document readXMLFromFile(String fileNameWithPath) throws Exception
    {
        // document builder
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        // parse file
        Document document = builder.parse(new File(fileNameWithPath));
        
        document.getDocumentElement().normalize();

        return document;
    }
}
