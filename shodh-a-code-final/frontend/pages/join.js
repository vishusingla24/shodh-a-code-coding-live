
import {useState} from 'react';
import {useRouter} from 'next/router';
export default function Join(){
  const [contest,setContest]=useState('SAMPLE123'); const [user,setUser]=useState('demo');
  const router = useRouter();
  function go(){ router.push('/contest/'+contest+'?user='+encodeURIComponent(user)); }
  return (<div style={{padding:20}}>
    <h1>Join Contest</h1>
    <input value={contest} onChange={e=>setContest(e.target.value)} /><br/>
    <input value={user} onChange={e=>setUser(e.target.value)} /><br/>
    <button onClick={go}>Join</button>
  </div>);
}
