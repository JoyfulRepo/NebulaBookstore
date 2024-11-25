import './App.css';
import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

import WelcomePage from "./pages/WelcomePage";
import HomePage from './pages/HomePage';
import ExploringPage from './pages/ExploringPage';
import WishlistPage from './pages/WishlistPage';
import CartPage from './pages/CartPage';
import ProfilePage from './pages/ProfilePage';
import RoleSelectPage from './pages/RoleSelectPage';
import CustomerLogin from './pages/CustomerLogin';
import CustomerCreateAccPage from './pages/CustomerCreateAccPage';
import PHistoryShipping from './pages/PHistoryShipping';
import PHistoryDelivered from './pages/PHistoryDelivered';
import PHistoryPrepare from './pages/PHistoryPrepare';
import SettingsPage from './pages/SettingsPage';


function App() {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<WelcomePage />} />
                <Route path='/Home' element={<HomePage />} />
                <Route path="/Explore" element={<ExploringPage />} />
                <Route path="/Login" element={<RoleSelectPage />} />
                <Route path='/Login/Customer' element={<CustomerLogin />} />
                <Route path='/Customer/CreateAccount' element={<CustomerCreateAccPage />} />
                <Route path="/Wishlist" element={<WishlistPage />} />
                <Route path="/Cart" element={<CartPage/>} />
                <Route path="/Profile" element={<ProfilePage />} />
                <Route path="/PurchaseHistory/Shipping" element={<PHistoryShipping />} />
                <Route path="/PurchaseHistory/Delivered" element={<PHistoryDelivered />} />
                <Route path="/PurchaseHistory/Preparing" element={<PHistoryPrepare />} />
                <Route path='/Settings' element={<SettingsPage />} />
            </Routes>
        </Router>
    );
}

export default App;

