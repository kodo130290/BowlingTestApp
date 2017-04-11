function OnlyNum(e)
{
	var keynum;
	var keychar;
	var numcheck;
	var return2;
	if(window.event)
	{
	keynum = e.keyCode;
	} else if(e.which) {
		keynum = e.which;
	}
	keychar = String.fromCharCode(keynum);
	if (keynum < 48 || keynum > 57) {
		return2 = false;
		if (keynum == 8) return2 = true;
	} else return2 = true;
	return return2; 
}