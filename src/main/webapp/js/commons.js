function openModal() {
    let gray_out = document.getElementById("fadeLayer");
    gray_out.style.visibility = "visible";
    setTimeout(addClass, 200);
}
function categoryopenModal() {
	
	const triggerCheckboxs = document.querySelectorAll('input[name="deleteId"]');

    let flag = false;
    for(const triggerCheckbox of triggerCheckboxs){
	    if (triggerCheckbox.checked) {
            flag = true;
            break;
        }
    }
    if(flag === false) {
	    return;
    }
    let gray_out = document.getElementById("fadeLayer");
    gray_out.style.visibility = "visible";
    setTimeout(addClass, 200);
}
function closeModal() {
    let modal = document.getElementById('modal');
    let gray_out = document.getElementById("fadeLayer");
    modal.classList.remove('is-show');
    gray_out.style.visibility ="hidden";
}

function addClass() {
    let modal = document.getElementById('modal');
    modal.classList.add('is-show');
}

function edit(id){
	
	if (document.getElementById(id).readOnly === true){
		document.getElementById(id).removeAttribute("readOnly");
		document.getElementById(id).style.background = "white";
		document.getElementById(id).style.color = "black";
	}else{
		document.getElementById(id).setAttribute("readOnly", true);
		document.getElementById(id).style.background = 'rgb(211,211,211)';
		document.getElementById(id).style.color = "black";
	}
}
