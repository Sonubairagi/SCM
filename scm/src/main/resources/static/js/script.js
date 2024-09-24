console.log("Script is loading...");

let currentTheme = getTheme();

document.addEventListener('DOMContentLoaded', () => {
    changeTheme();
});

function changeTheme() {

    console.log('inside changeTheme');

    //initiali code will run
    changePageTheme(currentTheme, currentTheme);

    

    //run when button click to change theme
    document.querySelector('#theme_change_btn').addEventListener('click', (event) => {

        let oldTheme = currentTheme;

        console.log('inside event')

        if(currentTheme === 'light') {
            currentTheme = 'dark';
        } else {
            currentTheme = 'light';
        }

        changePageTheme(currentTheme, oldTheme);

    });

}

//set current theme into localStorege
function setTheme(currentTheme) {
    localStorage.setItem('theme', currentTheme);
}

//get current theme from localStorege
function getTheme() {
    const theme = localStorage.getItem('theme');
    return theme ? theme : 'dark';
}

//run when button click to change theme
function changePageTheme(currentTheme, oldTheme) {

    setTheme(currentTheme);
    if(oldTheme){
        document.querySelector('html').classList.remove(oldTheme);
    }
    document.querySelector('html').classList.add(currentTheme);
    document.querySelector('#theme_change_btn').querySelector('span').textContent = currentTheme === 'light' ? 'Dark' : 'Light';
    
}