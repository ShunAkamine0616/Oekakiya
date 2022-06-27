document.getElementById("download_btn").addEventListener('click',event =>{
	document.getElementById("download_btn").disabled = true;
	document.getElementById("download_btn").style.backgroundColor = "#8aa3b9"
	setTimeout(function(){			
		document.getElementById("download_btn").disabled = false;
		document.getElementById("download_btn").style.backgroundColor = "#8ED0FF";
},3000);
    const downloadTag = document.createElement('a')
    downloadTag.href = document.getElementById("download").value
    downloadTag.download = document.getElementById("download_name").value
    downloadTag.click()
	fetch("DownloadCount")
.then(response => response.text())
.then(data => {
	
	document.getElementById("downloadNum").textContent = data;
})});