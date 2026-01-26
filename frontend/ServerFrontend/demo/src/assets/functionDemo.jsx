

function demo(){


function Button(){
    console.log("tmkc");
    return<button>hueue</button>//
}

const button=()=>{
    console.log("tmkc");
}

 <button onClick={()=>"tmkc"}></button>;
 <button onClick={button}></button>; // function reference

<button /> // function calling 

function parent(){
    function message(msg){
        console.log(msg);
    }
    return <child sendmessage={message}></child>;
}

function child(sendmessage){
    return <button onClick={()=>sendmessage("TMKC")}>SendMsg</button>
    // onClick={sendMessage("Hello Parent!")} // not like this here we are calling
   
}

// same
function hue(){}
const hue2=()=>{}

// event hadler error function
<button onClick={()=>setCount(count+1)}>Add</button>;

// on click of this button we are calling the hue function here
<button onClick={hue}>Click</button>;






}


