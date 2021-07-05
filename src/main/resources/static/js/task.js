/**
 * 
 */
document.getElementById("delete").onclick = function() {
	var result = confirm('削除しますか？');
	if( result ) {
 		return true;
 	}
	else {
    	return false;
 	}
}