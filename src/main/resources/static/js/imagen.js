function previewImage() {        
    var reader = new FileReader();         
    reader.readAsDataURL(document.getElementById('imagenpublicacion').files[0]);         
    reader.onload = function (e) {             
        document.getElementById('uploadPreview').src = e.target.result;         
    };     
}

function previewImage2() {        
    var reader = new FileReader();         
    reader.readAsDataURL(document.getElementById('imagenoferta').files[0]);         
    reader.onload = function (e) {             
        document.getElementById('uploadPreview').src = e.target.result;         
    };     
}