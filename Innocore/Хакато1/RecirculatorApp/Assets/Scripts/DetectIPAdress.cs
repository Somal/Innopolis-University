using UnityEngine;
using System.Collections;
using System.Net.NetworkInformation;
using System.Net;
using System.Net.Sockets;

public class DetectIPAdress : MonoBehaviour
{
		public UILabel thisLabel;
		// Use this for initialization
		void Start ()
		{
				//thisLabel.text += "1"
				thisLabel.text +=" "+ Network.player.externalIP.ToString ();
				thisLabel.text += " " + Network.player.externalPort.ToString ();
				thisLabel.text += " " + Network.player.ipAddress.ToString ();
				thisLabel.text += " " + Network.player.port.ToString ();
				thisLabel.text += " " +Application.internetReachability.ToString ();

		}
	

}
