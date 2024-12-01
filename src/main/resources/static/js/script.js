console.log("Script loded by lucky M.....");

// --------------------------  Change Theme logic   ---------------------------------------------------
let currentTheme=getTheme();
//initially -->

document.addEventListener("DOMContentLoaded",()=>{
    changeTheme();
});


//TODO:
function changeTheme(){
    //set to web page
    changePageTheme(currentTheme,currentTheme);
    //set to the listner to change the theme button
    const changeThemeBtn = document.querySelector('#theam_change_btn');

    
    changeThemeBtn.addEventListener("click",()=>{
        let oldTheme = currentTheme;
        // const oldTheme = currentTheme;
        if(currentTheme == "dark"){
            //theme-->"light"
            currentTheme="light";
        }else{
            //theme --> "dark"
            currentTheme="dark";
        }
        changePageTheme(currentTheme,oldTheme)
    });
}

//set theam to localstorage
function setTheme(theme ){
    localStorage.setItem("theme",theme);
}

//get theme to localstorage
function getTheme(){
    let theme = localStorage.getItem("theme");
    return theme ? theme: "light";
}

//change current page theme
function changePageTheme(theme,oldTheme){
//updating the localstorage
setTheme(currentTheme);
//remove the current theme
document.querySelector('html').classList.remove(oldTheme);
//adding the current theme
document.querySelector('html').classList.add(theme);
//change the text of button
document.querySelector('#theam_change_btn').querySelector("span").textContent = 
theme == "light" ? "Dark" : "Light";
}

// -------------------------- End of Change Theme logic   ---------------------------------------------------