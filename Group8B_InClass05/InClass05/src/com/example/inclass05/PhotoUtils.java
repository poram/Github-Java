/**
 * Assignment: In Class 05
 * FileName: PhotoUtils.Java
 * Names:
 * 	Udipta Roy
 * 	Sushmitha Yalla	
 *	Peter Oram 
 */

package com.example.inclass05;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.util.Xml;

public class PhotoUtils {

	static public class PhotoSAXParser extends DefaultHandler {
		ArrayList<String> photoUrlList;
		StringBuilder xmlInnerText;
		Photo photo;
		ArrayList<Photo> photoList;

		static public ArrayList<String> parsePhoto(InputStream in)
				throws IOException, SAXException {
			PhotoSAXParser parser = new PhotoSAXParser();
			Xml.parse(in, Xml.Encoding.UTF_8, parser);
			return parser.getPhotoList();
		}

		public ArrayList<String> getPhotoList() {
			return photoUrlList;
		}

		@Override
		public void startDocument() throws SAXException {
			super.startDocument();
			xmlInnerText = new StringBuilder();
			photoUrlList = new ArrayList<String>();
			photoList = new ArrayList<Photo>();
		}

		@Override
		public void startElement(String uri, String localName, String qName,
				Attributes attributes) throws SAXException {
			super.startElement(uri, localName, qName, attributes);
			if (localName.equals("photo")) {
				photo = new Photo();
				photo.setId(attributes.getValue("id"));
				photo.setUrl(attributes.getValue("url_m"));
			}
		}

		@Override
		public void endElement(String uri, String localName, String qName)
				throws SAXException {
			super.endElement(uri, localName, qName);
			if (localName.equals("photo")) {
				photoList.add(photo);
				photoUrlList.add(photo.getUrl());
			}
			xmlInnerText.setLength(0);
		}

		@Override
		public void characters(char[] ch, int start, int length)
				throws SAXException {
			super.characters(ch, start, length);
			xmlInnerText.append(ch, start, length);
		}

		@Override
		public void endDocument() throws SAXException {
			super.endDocument();
		}

	}

	static public class PhotoPullParser {

		static ArrayList<String> getPhotosUrls(InputStream in)
				throws XmlPullParserException, IOException {
			XmlPullParser parser = XmlPullParserFactory.newInstance()
					.newPullParser();
			parser.setInput(in, "UTF-8");
			ArrayList<String> photoUrlList = new ArrayList<String>();
			ArrayList<Photo> photoList = new ArrayList<Photo>();
			Photo photo = null;
			int event = parser.getEventType();
			while (event != XmlPullParser.END_DOCUMENT) {
				switch (event) {
				case XmlPullParser.START_TAG:
					if (parser.getName().equals("photo")) {
						photo = new Photo();
						photo.setId(parser.getAttributeValue(null, "id"));
						photo.setUrl(parser.getAttributeValue(null, "url_m"));
					}
					break;
				case XmlPullParser.END_TAG:
					if (parser.getName().equals("photo")) {
						photoList.add(photo);
						photoUrlList.add(photo.getUrl());
						photo = null;
					}
					break;
				default:
					break;
				}
				event = parser.next();
			}
			return photoUrlList;
		}
	}

}
