using UnityEngine;
using System.Collections;
using System.Net;
using System.IO;

public class ButtonScriptTest : MonoBehaviour
{
		public string url;
		public UILabel resultWidget;
		public enum Trigger
		{
				OnClick,
				OnMouseOver,
				OnMouseOut,
				OnPress,
				OnRelease,
				OnDoubleClick,
		}

		string Request (string url)
		{
				HttpWebRequest request = (HttpWebRequest)WebRequest.Create (url);
				HttpWebResponse response = (HttpWebResponse)request.GetResponse (); 
				StreamReader strm = new StreamReader (response.GetResponseStream ());
				string result = strm.ReadToEnd ();
				resultWidget.text = result;
				return result;
		}

		void OnClick ()
		{
				print ("clicked");
				try {
						print (Request (url));
				} catch {
						print ("ploblem");
				}
		}


}
