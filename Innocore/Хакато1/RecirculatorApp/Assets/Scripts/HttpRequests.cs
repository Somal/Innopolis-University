using UnityEngine;
using System.Collections;
using System.Net;



public class HttpRequests : MonoBehaviour {

	public string url="http://innopolisiot.890m.com/";
	public int HOST;
	public int  servodegree; 
	public int  dcdegree;

	void Request(string url)
	{
		HttpWebRequest request = (HttpWebRequest)WebRequest.Create(url);
		HttpWebResponse response = (HttpWebResponse)request.GetResponse(); 

	}

	// Update is called once per frame
	void Update(){
		Request (url + "/dc/50");
	}


}
