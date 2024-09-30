import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import axios from "axios";

export const validateUser = createAsyncThunk(
  "admin/validate",
  async (credentials) => {
    try {
      const response = await axios.post(
        "http://localhost:8089/auth/loginAdmin",
        credentials
      );
      if (response.status === 200) {
        return response.data; // Successful login
      }
      // If the server returns a different status, it will be handled below
    } catch (error) {}
  }
);


const userSlice = createSlice({
  name: "user",
  initialState: {
    user: null,
    status: "idle",
    message: "",
    registrationSuccess: false,
    validation: false,
    validationMessage: "",
  },
  reducers: {
    logout(state) {
      state.user = null;
      state.registrationSuccess = false;
      state.message = "";
      state.validation = false;
      state.validationMessage = "";
    },
  },
  extraReducers: (builder) => {
    builder.addCase(validateUser.pending, (state) => {
      state.status = "loading";
    });
    builder.addCase(validateUser.fulfilled, (state, action) => {
      state.status = "succeded";
      console.log(action.payload);
      if (action.payload != null) {
        state.validation = true;
        state.validationMessage = "Login Successful";
        state.user = action.payload;
      } else {
        state.validation = false;
        state.validationMessage = "Incorrect email or password";
      }
    });
    builder.addCase(validateUser.rejected, (state, action) => {
      state.status = "failed";
      state.error = action.error.message;
      state.validation = false;
      state.validationMessage = "Something went wrong..";
    });
  },
});

export const { logout } = userSlice.actions;
export default userSlice.reducer;
