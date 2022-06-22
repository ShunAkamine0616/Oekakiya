function openModal() {
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

document.addEventListener('DOMContentLoaded', function(event) {
  const targetButton = document.getElementById('submitButton');
  const triggerCheckbox = document.querySelector('input[name="deleteId"]');

  targetButton.disabled = true;
  targetButton.classList.add('is-inactive');

  triggerCheckbox.addEventListener('change', function() {
    if (this.checked) {
      targetButton.disabled = false;
      targetButton.classList.remove('is-inactive');
      targetButton.classList.add('is-active');
    } else {
      targetButton.disabled = true;
      targetButton.classList.remove('is-active');
      targetButton.classList.add('is-inactive');
    }
  }, false);
}, false);