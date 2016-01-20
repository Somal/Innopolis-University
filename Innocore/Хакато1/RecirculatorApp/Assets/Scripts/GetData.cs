using UnityEngine;
using System.Collections;
using System.Net;	
using System.IO;	
using System;

public class GetData : MonoBehaviour
{
		private string url;
		
		//private TextAsset asset;
		public string id;
		public UILabel temperature, humidity, air;
		
		public string getData (string id)
		{
				string url = "http://innoiothack.cloudapp.net:8080/add_dataphone.php?id=" + id;
				print (url);
				HttpWebRequest request = (HttpWebRequest)WebRequest.Create (url);
				HttpWebResponse response = (HttpWebResponse)request.GetResponse (); 
				StreamReader strm = new StreamReader (response.GetResponseStream ());
				string result = strm.ReadToEnd ();
				print (result);
				return result;
		}
	
		void OnClick ()
		{
				print ("clicked on  get data");
				string[] result = getData (id).Split (' ');
				temperature.text = result [1];	
				humidity.text = result [3];
				air.text = result [4];
				print (result);
				try {

				} catch {
						print ("ploblem");
				}
		}

		

}

