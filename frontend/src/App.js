import './App.css';
import React, {Component} from "react";
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import Areas from "./components/Areas";

class App extends Component {

    render() {
        return (
            <div>
                <Router>
                    <Routes>
                        <Route path='/' exact={true} element={<Areas/>}/>
                        <Route path='/areas' exact={true} element={<Areas/>}/>
                    </Routes>
                </Router>
            </div>
        )
    }

}

export default App;
