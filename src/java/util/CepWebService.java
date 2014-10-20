/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import Entity.Localidades;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 *
 * @author cleber
 */
public class CepWebService {
 
  
    @SuppressWarnings("rawtypes")
    public List<Localidades> getCepWebService(String cep) {
         List<Localidades> localidades = new ArrayList<Localidades>();
        try {
            URL url = new URL(
                    "http://cep.republicavirtual.com.br/web_cep.php?cep=" + cep
                            + "&formato=xml");
 
            Document document = getDocumento(url);
 
            Element root = document.getRootElement();
            Localidades l= new Localidades();
            l.setCep(cep);
            for (Iterator i = root.elementIterator(); i.hasNext();) {
                
                Element element = (Element) i.next();
 
                if (element.getQualifiedName().equals("uf"))
                   l.setUf(element.getText());
 
                if (element.getQualifiedName().equals("cidade"))
                    l.setCidade(element.getText());
 
                if (element.getQualifiedName().equals("bairro"))
                    l.setBairro(element.getText());
 
                if (element.getQualifiedName().equals("tipo_logradouro"))
                    l.setTpLogradouro(element.getText());
 
                if (element.getQualifiedName().equals("logradouro"))
                    l.setLogradouro(element.getText());
 
              //  if (element.getQualifiedName().equals("resultado"))
              //      setResultado(Integer.parseInt(element.getText()));
               
            }
            localidades.add(l);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return localidades;
    }

   
    
    
    public Document getDocumento(URL url) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(url);
 
        return document;
    }
}