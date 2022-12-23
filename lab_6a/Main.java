import java.util.*;
import java.io.*;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import static java.lang.Double.max;
import static java.lang.Math.sqrt;

public class Main {
    public static void main(String[] args) throws IOException {
        FileReader reader = new FileReader("input.txt");
        Scanner in = new Scanner(reader);
        FileWriter writer = new FileWriter("output.txt");
        int n, m, OY, OX, R, s_i, s_j, e_i, e_j;
        double dist_1 = 0, dist_2 = 0, dist_3 = 0, dist_4 = 0, dist = 0;
        TreeMap map = new TreeMap();
        String coord;
        n = in.nextInt();
        m = in.nextInt();
        OX = in.nextInt();
        OY = in.nextInt();
        R = in.nextInt();
        s_i = OY - R;
        s_j = OX - R;
        e_i = OY + R;
        e_j = OX + R;
        int[][] data = new int[n][m];
        int[][] figure = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(figure[i], 0);
        }
        for (int i = s_i; i < e_i; i++) {
            for (int j = s_j; j < e_j; j++) {
                dist_1 = sqrt((OY - i) * (OY - i) + (OX - j) * (OX - j));
                dist_2 = sqrt((OY - i) * (OY - i) + (j - OX + 1) * (j - OX + 1));
                dist_4 = sqrt((i - OY + 1) * (i - OY + 1) + (j - OX + 1) * (j - OX + 1));
                dist_3 = sqrt((i - OY + 1) * (i - OY + 1) + (OX - j) * (OX - j));
                dist = max(max(dist_1, dist_2), max(dist_3, dist_4));
                if (dist < R) {
                    coord = "0";
                    figure[i][j] = 1;
                    coord = "(" + j + ", " + i + ")";
                    map.put(coord, dist);
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                writer.write(figure[i][j] + " ");
            }
            writer.write("\n");
        }
        Set set = map.entrySet();
        Iterator I = set.iterator();
        while (I.hasNext()) {
            Map.Entry me = (Map.Entry) I.next();
            writer.write(me.getKey() + ": " + me.getValue());
            writer.write("\n");
        }
        map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(System.out::println);

        reader.close();
        writer.close();

        try
        {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse("file.xml");
            Node root = document.getDocumentElement();

            System.out.println("List of circles:");
            System.out.println();

            NodeList circles = root.getChildNodes();
            for (int i = 0; i < circles.getLength(); i++)
            {
                Node circle = circles.item(i);
                if (circle.getNodeType() != Node.TEXT_NODE)
                {
                    NodeList circleProps = circle.getChildNodes();
                    for(int j = 0; j < circleProps.getLength(); j++) {
                        Node circleProp = circleProps.item(j);
                        if (circleProp.getNodeType() != Node.TEXT_NODE) {
                            System.out.println(circleProp.getNodeName() + ":" + circleProp.getChildNodes().item(0).getTextContent());
                        }
                    }
                    System.out.println("-------------------------------");
                }
            }

            addNewCircle(document);

        } catch (ParserConfigurationException ex) {
            ex.printStackTrace(System.out);
        } catch (SAXException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }

    private static void addNewCircle(Document document) throws TransformerFactoryConfigurationError, DOMException {
        Node root = document.getDocumentElement();
        Element circle = document.createElement("Circle");
        Element table = document.createElement("Table");
        table.setTextContent("5 6");
        Element center = document.createElement("Center");
        center.setTextContent("2 3");
        Element radius = document.createElement("Radius");
        radius.setTextContent("2");

        circle.appendChild(table);
        circle.appendChild(center);
        circle.appendChild(radius);
        root.appendChild(circle);

        writeDocument(document);
    }

    // Функция для сохранения DOM в файл
    private static void writeDocument(Document document) throws TransformerFactoryConfigurationError {
        try {
            Transformer tr = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(document);
            FileOutputStream fos = new FileOutputStream("other.xml");
            StreamResult result = new StreamResult(fos);
            tr.transform(source, result);
        } catch (TransformerException | IOException e) {
            e.printStackTrace(System.out);
        }

    }
}