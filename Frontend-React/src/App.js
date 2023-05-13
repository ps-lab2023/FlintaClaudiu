import Login from "./components/Login&Register/Login";
import MainSearch from "./components/MainSearch";
import Header from "./components/Header";
import Register from "./components/Login&Register/Register";
import Forgot from "./components/Login&Register/Forgot";
import MainSearchLogged from "./components/NormalUser/MainSearchLogged";
import MainAdmin from "./components/Admin/MainAdmin";
import { BrowserRouter,Routes,Route } from "react-router-dom";
import HotelsAdmin from "./components/Admin/HotelsAdmin";
import RoomsAdmin from "./components/Admin/RoomsAdmin";
import ImageList from "./components/Lists/ImageList";
import ChangePassAdmin from "./components/Admin/ChangePassAdmin";
import HotelsPage from "./components/HotelsPage";
import HotelsPage2 from "./components/HotelsPage2";
import RoomsPage from "./components/RoomsPage";
import ImageSlider from "./components/Lists/ImageSlider";
import ChangePassNormal from "./components/NormalUser/ChangePassNormal";
import ConnectedUsersAdmin from "./components/Admin/ConnectedUsersAdmin";
import RoomsPageFiltered from "./components/RoomsPageFiltered";
import RoomsPageLogged from "./components/RoomsPageLogged";
import RoomsPageFilteredUnlogged from "./components/RoomsPageFilteredUnlogged";
import HotelsPageLogged from "./components/HotelsPageLogged";
import FinalReservationMessage from "./components/FinalReservationMessage";
import ChatRoom from "./components/Chat/ChatRoom";
import HotelsPageLogged2 from "./components/HotelsPageLogged2";
import ReservationList from "./components/NormalUser/ReservationList";
import AllHotelsLogged from "./components/AllHotelsLogged";
import AllHotels from "./components/AllHotels";
import ForgotPass from "./components/ForgotPass";

function App() {
  return (
    <div>
      <BrowserRouter>
            <Routes>
              <Route path="/login" element= { <Login />} />
              <Route path="/admin/hotels" element= { <HotelsAdmin />} />
              <Route path="/admin/rooms" element= { <RoomsAdmin />} />
              <Route path="/admin" element= { <MainAdmin />} />
              <Route path="/register" element= { <Register/>} />
              <Route path="/home" element= { <MainSearchLogged/>} />
              <Route path="/" element= { <MainSearch/>} />
              <Route path="admin/changePass" element= { <ChangePassAdmin/>} />
              <Route path="home/changePass" element= { <ChangePassNormal/>} />
              <Route path="/hotels_unlogged" element={<HotelsPage />} />
              <Route path="/all_hotels_unlogged" element={<AllHotels />} />
              <Route path="/hotels_unlogged2" element={<HotelsPage2 />} />
              <Route path="/hotels" element={<HotelsPageLogged />} />
              <Route path="/all_hotels" element={<AllHotelsLogged />} />
              <Route path="/hotels2" element={<HotelsPageLogged2 />} />
              <Route path="/hotels_unlogged/rooms_unlogged" element={<RoomsPage />} />
              <Route path="/hotels_unlogged2/rooms_unlogged" element={<RoomsPage />} />
              <Route path="/hotels/rooms" element={<RoomsPageLogged />} />
              <Route path="/hotels2/rooms" element={<RoomsPageLogged />} />
              <Route path="/hotels_unlogged/rooms_unlogged/dates_unlogged" element={<RoomsPageFilteredUnlogged />} />
              <Route path="/hotels_unlogged2/rooms_unlogged/dates_unlogged" element={<RoomsPageFilteredUnlogged />} />
              <Route path="/hotels/rooms/dates" element={<RoomsPageFiltered />} />
              <Route path="/hotels2/rooms/dates" element={<RoomsPageFiltered />} />
              <Route path="reservation" element={<FinalReservationMessage />} />
              <Route path="admin/viewUsers" element={<ConnectedUsersAdmin />} />
              <Route path="/chat" element={<ChatRoom />} />
              <Route path="/home/reservationList" element={<ReservationList />} />
              <Route path="/forgot/forgot_pass" element={<ForgotPass />} />
              <Route path="/forgot" element={<Forgot />} />
              
            </Routes>
        </BrowserRouter>
    </div>
  );
}

export default App;
