document.getElementById("download_btn").addEventListener('click',event =>{
	fetch("DownloadCount")
.then(response => response.text())
.then(data => {
	console.log(data);
	document.getElementById("downloadNum").textContent = data;
})});