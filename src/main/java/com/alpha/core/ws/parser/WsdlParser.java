package com.alpha.core.ws.parser;

import com.alpha.core.ws.entity.Wsdl;
import com.alpha.core.ws.exception.CommonException;
import com.alpha.core.ws.utils.Constants;
import com.alpha.core.ws.utils.ILog;
import org.springframework.util.StringUtils;

import javax.wsdl.*;
import javax.wsdl.extensions.ExtensibilityElement;
import javax.wsdl.extensions.soap.SOAPAddress;
import javax.wsdl.factory.WSDLFactory;
import javax.wsdl.xml.WSDLReader;
import javax.xml.namespace.QName;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.Map.Entry;
import java.util.function.Consumer;

public class WsdlParser implements ILog {

    public static Set<String> getWsdl(String urlAddress) throws CommonException {
        Set<String> wsdlSet = new TreeSet<String>();
        URL url = null;
        BufferedReader br = null;
        try {
            url = new URL(urlAddress);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            br = new BufferedReader(
                    new InputStreamReader(urlConnection.getInputStream()));
            String line = br.readLine();
            String[] stringArray = line.split("<a href=\"");
            for (int i = 1; i < stringArray.length; ++i) {
                String wsdlUrl = stringArray[i].split("\">", 2)[0];
                if (wsdlUrl.endsWith(Constants.WSDL_SUFFIX)) {
                    wsdlSet.add(wsdlUrl);
                }
            }
        } catch (MalformedURLException e) {
            throw new CommonException(e);
        } catch (IOException e) {
            throw new CommonException(e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
        }
        return wsdlSet;
    }

    public static void readWsdl(String wsdlUrl, Consumer<Wsdl> consumer) throws CommonException {
        try {
            WSDLFactory factory = WSDLFactory.newInstance();
            WSDLReader reader = factory.newWSDLReader();
            reader.setFeature("javax.wsdl.verbose", true);
            reader.setFeature("javax.wsdl.importDocuments", true);
            Definition def = reader.readWSDL(wsdlUrl);
            String namespace = def.getNamespace("tns");
            Map<QName, Service> serviceMap = def.getAllServices();
            Iterator<Entry<QName, Service>> serviceIter = serviceMap.entrySet().iterator();
            while (serviceIter.hasNext()) {
                Entry<QName, Service> serviceEntry = serviceIter.next();
                Map<String, Port> portMap = serviceEntry.getValue().getPorts();
                Iterator<Entry<String, Port>> portIter = portMap.entrySet().iterator();
                while (portIter.hasNext()) {
                    Entry<String, Port> portEntry = portIter.next();
                    Port port = portEntry.getValue();
                    Wsdl wsdlInfo = new Wsdl();
                    List<ExtensibilityElement> extensibilityElementList = port
                            .getExtensibilityElements();
                    for (ExtensibilityElement extensibilityElement : extensibilityElementList) {
                        if (extensibilityElement instanceof SOAPAddress) {
                            SOAPAddress soapAddress = (SOAPAddress) extensibilityElement;
                            wsdlInfo.setAddress(soapAddress.getLocationURI());
                        }
                    }
                    if (StringUtils.isEmpty(wsdlInfo.getAddress())) {
                        continue;
                    }
                    Binding binding = port.getBinding();
                    wsdlInfo.setWsdl(wsdlUrl);
                    wsdlInfo.initFacadeClass(namespace, binding.getPortType().getQName()
                            .getLocalPart());
                    List<BindingOperation> bindingOperationList = binding.getBindingOperations();
                    for (BindingOperation operation : bindingOperationList) {
                        wsdlInfo.addOperation(operation.getName());
                    }
                    consumer.accept(wsdlInfo);
                }
            }

        } catch (WSDLException e) {
            throw new CommonException(e);
        }
    }

}
