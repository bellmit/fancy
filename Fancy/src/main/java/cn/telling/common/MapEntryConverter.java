package cn.telling.common;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;


class MapEntryConverter implements Converter {
	public static final String CDATA_START = "<![CDATA["; 
    public static final String CDATA_END = "]]>"; 
    
    private String[] notCdataField;
    
    public MapEntryConverter(String... noCdataField) {
    	notCdataField = noCdataField;
    }
    
	@SuppressWarnings("rawtypes")
	public boolean canConvert(Class clazz) {
		return AbstractMap.class.isAssignableFrom(clazz);
	}

	@SuppressWarnings("unchecked")
	public void marshal(Object values, HierarchicalStreamWriter writer, MarshallingContext context) {
		AbstractMap<String, String> map = (AbstractMap<String, String>)values;
		for (Entry<String, String> entry : map.entrySet()) {
			String key = entry.getKey().toString();
			String value = entry.getValue().toString();
			writer.startNode(key);
			if (notCdataField != null && !Arrays.asList(notCdataField).contains(key)) {
				value = CDATA_START + value + CDATA_END;
			}
			writer.setValue(value);
			writer.endNode();
		}
	}

	public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
		Map<String, String> map = new LinkedHashMap<String, String>();

		while (reader.hasMoreChildren()) {
			reader.moveDown();
			String value = reader.getValue();
			int cdataStart = value.indexOf(CDATA_START);
			int cdataEnd = value.lastIndexOf(CDATA_END);
			if (cdataStart == 0 && cdataEnd > 0) {
				value = value.substring(cdataStart, cdataEnd);
			}
			map.put(reader.getNodeName(), reader.getValue());
			reader.moveUp();
		}
		return map;
	}
}