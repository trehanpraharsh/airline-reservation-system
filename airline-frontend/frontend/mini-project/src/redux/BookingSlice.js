import { createSlice } from "@reduxjs/toolkit";
import { createAsyncThunk } from "@reduxjs/toolkit";
import axios from "axios";

export const createBooking = createAsyncThunk(
  "booking/create",
  async (bookingData) => {
    const response = await axios.post(
      "http://localhost:8086/booking/create",
      bookingData
    );
    console.log(response);
    return response.data;
  }
);

export const createPayment = createAsyncThunk(
  "/payment/create",
  async (paymentData) => {
    console.log(paymentData);
    const response = await axios.put(
      "http://localhost:8087/payments/process",
      paymentData
    );
    console.log(response);
    return response.data;
  }
);

export const updateFlightSeats=createAsyncThunk('/flights/updateSeats', async(flightID, {getState})=>{
  const state=getState()
  const seatType=state.booking.passengerData[0].travelClass
  console.log(seatType)
  const response=await axios.put(`http://localhost:8081/flights/updateseatcount/${flightID}/${seatType}`)
  return response.data
})

const bookingSlice = createSlice({
  name: "booking",
  initialState: {
    passengerData: [],
    amount:null,
    fightId: null,
    bookingId: null,
    paymentId: null,
  },
  reducers: {
    addPassenger: (state, action) => {
      state.passengerData.push(action.payload);
    },
    setFlightId: (state, action) => {
      state.flightId = action.payload;
    },
    setBookingId: (state, action) => {
      state.bookingId = action.payload;
    },
    setAmount: (state, action) => {
      state.amount = action.payload;
    },
    setPaymentId: (state, action) => {
      state.paymentId = action.payload;
    },
  },
  extraReducers: (builder) => {
    builder.addCase(createBooking.fulfilled, (state, action) => {
      state.bookingId = action.payload.bookingId;
    });
  },
});

export const { addPassenger, setFlightId, setAmount, setBookingId, setPaymentId } =
  bookingSlice.actions;
export default bookingSlice.reducer;
