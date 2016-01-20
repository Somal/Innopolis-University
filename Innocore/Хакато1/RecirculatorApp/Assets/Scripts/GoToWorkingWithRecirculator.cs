using UnityEngine;
using System.Collections;

public class GoToWorkingWithRecirculator : MonoBehaviour {
	//public/ GameObject notDestroyed;
	// Use this for initialization
	void OnClick(){
		//DontDestroyOnLoad (notDestroyed);
		Application.LoadLevel(Application.loadedLevel+1);
	}
	

}
