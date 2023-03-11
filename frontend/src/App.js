import axios from 'axios';
import { Fragment, useEffect, useState } from 'react';
import  { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import BodyCreateAccount from './components/BodyCreateAccount/BodyCreateAccount';
import BodyLogin from './components/BodyLogin';
import BodySubmit from "./components/BodySubmit";
import BodyTextos from "./components/BodyTextos";
import Footer from "./components/Footer";
import Header from "./components/Header";
import MyAccount from './components/MyAccount/MyAccount';

function App() {

    const [loggedUser, setLoggedUser] = useState(sessionStorage.getItem('token') !== null)
    const [textCounter, setTextCounter] = useState(0)
    
    // Token renew every hour
    const TOKEN_RENEW_MS = 1000 * 60 * 60;

    useEffect(() => {
        const interval = setInterval(() => {
            
            // Resgata token e invalida storage
            var token = sessionStorage.getItem('token')
            if(token !== null)
                sessionStorage.removeItem('token')
            
            // Se o token é válido, requisita um novo token
            // Se é inválido, 
            if(loggedUser && token != null){
                const config = {
                    headers: { Authorization: `Bearer ${token}` }
                };
                
                axios.get("/api/token_reflesh", config)
                .then(response => {
                    sessionStorage.setItem('token', response.data.token)
                    setLoggedUser(true)
                })
            } else {
                // Se token já estava nulo, avisa todo mundo
                setLoggedUser(false)
            }
        }, TOKEN_RENEW_MS);

        return () => clearInterval(interval);
    }, [loggedUser, TOKEN_RENEW_MS])

    return (
        <Fragment>
            <Header loggedUser={loggedUser}/>
            <Router>
                <Routes>
                    <Route path="/" element={<BodyTextos/>}/>
                    <Route path="/login" element={<BodyLogin setLoggedUser={setLoggedUser}/>}/>
                    <Route path="/filter" element={<BodyTextos textCounter={textCounter}/>}/>
                    <Route path="/submit" element={<BodySubmit textCounter={textCounter}
                                                               setTextCounter={setTextCounter}
                                                               loggedUser={loggedUser}/>}
                    />
                    <Route path="/create_account" element={<BodyCreateAccount loggedUser={loggedUser}
                                                                              setLoggedUser={setLoggedUser}/>}/>
                    <Route path="/my_account" element={<MyAccount loggedUser={loggedUser}
                                                                  setLoggedUser={setLoggedUser}/>}
                    />
                </Routes>
            </Router>
            <Footer
                titulo={"Micropensamento"}
                autor={"Walisson Gomes"}
                ano={"2023"}/>
        </Fragment>
    );
}

export default App;
