using UnityEngine;
using System.Collections;
using System.Net;
using System.IO;

public class OnOff : MonoBehaviour
{
		private string url;
		public string id;

		string Request (string id, string status)
		{
				url += "http://innoiothack.cloudapp.net:8080/include.php?id=" + id + "&status=" + status;
				print (url);
				HttpWebRequest request = (HttpWebRequest)WebRequest.Create (url);
				HttpWebResponse response = (HttpWebResponse)request.GetResponse (); 
				//StreamReader strm = new StreamReader (response.GetResponseStream ());
				//string result = strm.ReadToEnd ();
				//return result;
				return "";
		}

		void OnClick ()
		{
				print ("clicked on or off");
				
				GetData gd = new GetData ();
				string res = gd.getData (id);
				res = res.Split (' ') [2];
				if (res == "0")
						res = "1";
				else
						res = "0";

				print (Request (id, res));

		}

		

		

}
