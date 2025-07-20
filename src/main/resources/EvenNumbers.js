function init(){
    sortArrayInDescendingOrder([5, 12, 3, 21, 8]);
    formatDate();
    getEvenNumbers([1,2]);
    checkPalindrome('aba');
    isValidEmail('test@example.com');
    isValidEmail('invalid-email@@');
    filterPrice(products, 100, 1000);
    countWord("Hello world Hello");
}
function getEvenNumbers(a){
    document.getElementById('evenNumbers').textContent = a.filter(n => n % 2 === 0);
}
function checkPalindrome(b){
    let c = "";
    let start_index = b.length-1;
    for(let i = start_index ; i > -1; i--){
        c = c + b.charAt(i);
    }
    document.getElementById('checkPalindrome').textContent = `${b} and is ${(c === b)} what scary be funny`;
}
function sortArrayInDescendingOrder(a){
    document.getElementById('sortArray').textContent = a.sort((a,b) => b - a);
}
function formatDate(parameterDate = new Date()){
    document.getElementById('formatDate').textContent = `${parameterDate.getFullYear()}/${parameterDate.getMonth()}/${parameterDate.getUTCDate()}`;
}
function isValidEmail(emailAddress = ""){
    let signIndex = emailAddress.indexOf('@');
    let dotIndex = emailAddress.indexOf('.');
    if(emailAddress.lastIndexOf('@') > signIndex
        || emailAddress.lastIndexOf('.') > dotIndex
        || signIndex === -1
        || dotIndex === -1
    ){
        document.getElementById('inValidEmail').textContent = emailAddress;
    } else {
        document.getElementById('validEmail').textContent = emailAddress;
     }
}
function countWord(words = ""){
    document.getElementById('array').textContent = words.split(' ');
}
const products = [
    {name: "LapTop", price: 1200},
    {name: "Phone", price: 800},
    {name: "Book", price: 20}
    ];
function filterPrice(products = [], fromPrice, toPrice){
    document.getElementById('filterPrice').textContent = products.filter(e => e.price >= fromPrice && e.price <= toPrice);
}