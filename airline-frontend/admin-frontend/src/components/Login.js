import React, { useState, useEffect } from 'react'
import "../login.css"
import { useDispatch, useSelector } from 'react-redux'
import { useLocation } from 'react-router-dom'
import { addUser, validateUser } from '../redux/userSlice'
import { useNavigate } from 'react-router-dom'
import { Link } from 'react-router-dom'
import Cookies from 'js-cookie'

const Login = () => {

    const [formData, setFormData]=useState({
        airlineEmail:'',
        airlinePassword:''
    })


    const dispatch=useDispatch();
    const navigate=useNavigate();
    const location=useLocation();
    const validation=useSelector((state)=>state.user.validation)
    let validationMessage=useSelector((state)=>state.user.validationMessage)
    const user=useSelector((state)=>state.user.user)
    const error=useSelector((state)=>state.user.error);

    // useEffect(()=>{
    //     if()
    //     {
    //         //history.push
    //     }
    // })

    const validEmail=new RegExp('^[^\\s@]+@[^\\s@]+\.[^\\s@]+$')

    const [errors, setErrors]=useState({})

    const handleChange=(e)=>{
        setFormData({...formData,[e.target.name]:e.target.value})
    }

    const handleSubmit=(e)=>{
        console.log(formData)
        e.preventDefault();
        const newErrors={};

        if(!formData.airlineEmail)
            newErrors.airlineEmail="*email is required"
        else if(!validEmail.test(formData.airlineEmail))
            newErrors.airlineEmail="*enter a valid email"
        if(!formData.airlinePassword)
            newErrors.airlinePassword="*enter a password"

        setErrors(newErrors);

        if(Object.keys(newErrors).length===0)
        {
            const admin=dispatch(validateUser(formData))
            console.log(admin)
            if(validation)
            {
                setFormData({airlineEmail:'',airlinePassword:''})
                validationMessage='';
                Cookies.set('admin',JSON.stringify(user))
                navigate('/view-flights')
            }
        }
    }

  return (
    <div className='login-cont'>
                <Link to="/" style={{textDecoration:'none', color:'black'}}><h3 className='login-logo'>SUNFLY</h3></Link>
        <div className='login-main'>
            <div className='login-left-side'>
            <div className='login-register-headings'>
                    <h3>Welcome back, Admin</h3>
                </div>
                <form onSubmit={handleSubmit}>
                    <div className='form-floating mb-4'>
                            <input type="text" placeholder='Email' className={`form-control ${errors.airlineEmail ? 'is-invalid' : ''}`} name='airlineEmail' value={formData.airlineEmail} onChange={handleChange}></input>
                            <label for='email'>Email</label>
                    </div>
                    <div className='form-floating mb-4'>
                            <input type="password" placeholder='Password' className={`form-control ${errors.airlinePassword ? 'is-invalid' : ''}`} name='airlinePassword' value={formData.airlinePassword} onChange={handleChange}></input>
                            <label for='airlinePassword'>Password</label>
                    </div>
                    <div className='' style={{marginBottom:'1rem'}}>
                    {errors.airlineEmail&&<><span className='error'>{errors.airlineEmail}</span><br /></>}
                    {errors.password&&<><span className='error'>{errors.password}</span><br /></>}
                    {console.log(validationMessage)}
                    {validationMessage&&<p style={{fontSize:'18px'}}>{validationMessage}</p>}
                    </div>

                    <button className='btn btn-primary login-button'>Login</button>
                </form>
            </div>
        </div>
    </div>
  )
}

export default Login