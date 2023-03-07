import { Fragment, useState } from 'react';
import  { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import AccountCreated from './components/BodyCreateAccount/AccountCreated';
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
                    <Route path="/create_account" element={<BodyCreateAccount/>}/>
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
