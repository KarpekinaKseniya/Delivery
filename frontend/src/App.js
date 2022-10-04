import './App.css';
import React, {Component} from "react";
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import Areas from "./components/Areas";
import 'react-notifications/lib/notifications.css';
import {NotificationContainer} from "react-notifications";

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
                <NotificationContainer/>
            </div>
        )
    }

}

export default App;
