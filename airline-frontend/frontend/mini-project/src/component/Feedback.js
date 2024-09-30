import React, { useEffect, useState } from "react";
import { motion } from "framer-motion";
import { useDispatch, useSelector } from "react-redux";
import { updateFlightSeats } from "../redux/BookingSlice";

export default function Feedback() {
  const [feedback, setFeedback] = useState("");
  const [isSubmitted, setIsSubmitted] = useState(false);
  
  const dispatch=useDispatch();

  const flightId=useSelector((state)=>state.booking.flightId)
  let numPassengers=useSelector((state)=>state.booking.passengerData.length)

  useEffect(()=>{
    const updateSeats = async () => {
      for (let i = 0; i < numPassengers; i++) {
        await dispatch(updateFlightSeats(flightId)); // Wait for the dispatch to complete
      }
    };

    updateSeats();
  },[])

  const handleSubmit = (e) => {
    e.preventDefault();
    // Here you would typically send the feedback to your server
    console.log("Feedback submitted:", feedback);
    setIsSubmitted(true);
  };

  const containerStyle = {
    minHeight: "100vh",
    background: "linear-gradient(to bottom right, #60A5FA, #A78BFA)",
    display: "flex",
    alignItems: "center",
    justifyContent: "center",
    padding: "1rem",
  };

  const cardStyle = {
    backgroundColor: "white",
    borderRadius: "0.5rem",
    boxShadow:
      "0 20px 25px -5px rgba(0, 0, 0, 0.1), 0 10px 10px -5px rgba(0, 0, 0, 0.04)",
    padding: "2rem",
    width: "100%",
    maxWidth: "28rem",
  };

  const titleStyle = {
    fontSize: "1.875rem",
    fontWeight: "bold",
    textAlign: "center",
    color: "#1F2937",
    marginBottom: "1.5rem",
  };

  const iconStyle = {
    color: "#10B981",
    fontSize: "6rem",
    marginBottom: "1.5rem",
    display: "flex",
    justifyContent: "center",
  };

  const formStyle = {
    display: "flex",
    flexDirection: "column",
    gap: "1rem",
  };

  const subtitleStyle = {
    fontSize: "1.25rem",
    fontWeight: "600",
    color: "#4B5563",
    textAlign: "center",
  };

  const textareaStyle = {
    width: "100%",
    padding: "0.75rem",
    border: "1px solid #D1D5DB",
    borderRadius: "0.375rem",
    resize: "none",
    outline: "none",
  };

  const buttonStyle = {
    width: "100%",
    backgroundColor: "#3B82F6",
    color: "white",
    padding: "0.5rem 1rem",
    borderRadius: "0.375rem",
    border: "none",
    cursor: "pointer",
    transition: "background-color 0.3s ease-in-out",
  };

  const thankYouStyle = {
    textAlign: "center",
    fontSize: "1.25rem",
    color: "#10B981",
    fontWeight: "600",
  };

  return (
    <div style={containerStyle}>
      <motion.div
        initial={{ opacity: 0, y: 20 }}
        animate={{ opacity: 1, y: 0 }}
        transition={{ duration: 0.5 }}
        style={cardStyle}
      >
        <motion.h1
          initial={{ opacity: 0, y: -20 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ delay: 0.2, duration: 0.5 }}
          style={titleStyle}
        >
          Payment Successful!
        </motion.h1>
        <motion.div
          initial={{ scale: 0, opacity: 0 }}
          animate={{ scale: 1, opacity: 1 }}
          transition={{ delay: 0.4, duration: 0.5, type: "spring" }}
          style={iconStyle}
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            viewBox="0 0 20 20"
            fill="currentColor"
            style={{ width: "6rem", height: "6rem" }}
          >
            <path
              fillRule="evenodd"
              d="M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z"
              clipRule="evenodd"
            />
          </svg>
        </motion.div>
        {!isSubmitted ? (
          <motion.form
            initial={{ opacity: 0, y: 20 }}
            animate={{ opacity: 1, y: 0 }}
            transition={{ delay: 0.6, duration: 0.5 }}
            onSubmit={handleSubmit}
            style={formStyle}
          >
            <h2 style={subtitleStyle}>We'd love your feedback!</h2>
            <textarea
              value={feedback}
              onChange={(e) => setFeedback(e.target.value)}
              placeholder="Please share your experience with our platform..."
              rows={4}
              style={textareaStyle}
              required
            />
            <button type="submit" style={buttonStyle}>
              Submit Feedback
            </button>
          </motion.form>
        ) : (
          <motion.div
            initial={{ opacity: 0, scale: 0.8 }}
            animate={{ opacity: 1, scale: 1 }}
            transition={{ duration: 0.5 }}
            style={thankYouStyle}
          >
            Thank you for your feedback!
          </motion.div>
        )}
      </motion.div>
    </div>
  );
}
