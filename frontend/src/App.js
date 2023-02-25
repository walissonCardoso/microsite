import { Fragment } from 'react';
import  { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import BodySubmit from "./components/BodySubmit";
import BodyTextos from "./components/BodyTextos";
import Footer from "./components/Footer";
import Header from "./components/Header";

function App() {
    return (
        <Fragment>
            <Header/>
            <Router>
                <Routes>
                    <Route path="/" element={<BodyTextos/>}/>
                    <Route path="/login" element={<BodySubmit/>}/>
                    <Route path="/filter" element={<BodyTextos/>}/>
                    <Route path="/submit" element={<BodySubmit/>}/>
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
