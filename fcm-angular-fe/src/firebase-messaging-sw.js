importScripts("https://www.gstatic.com/firebasejs/10.7.0/firebase-app-compat.js");
importScripts("https://www.gstatic.com/firebasejs/10.7.0/firebase-messaging-compat.js");

const firebaseConfig = {
    apiKey: "config data from firebase console",
    authDomain: "config data from firebase console",
    projectId: "config data from firebase console",
    storageBucket: "config data from firebase console",
    messagingSenderId: "config data from firebase console",
    appId: "config data from firebase console"
};

firebase.initializeApp(firebaseConfig);
const messaging = firebase.messaging();
