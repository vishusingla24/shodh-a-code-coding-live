
import {useState, useEffect} from 'react';
import {useRouter} from 'next/router';
export default function Contest(){
  const router = useRouter(); const {id} = router.query;
  const [problem, setProblem] = useState({title:'Add A+B', statement:'Add two numbers'});
  const [code,setCode]=useState('public class Solution { public static void main(String[]a){ java.util.Scanner s=new java.util.Scanner(System.in); int x=s.nextInt(); int y=s.nextInt(); System.out.println(x+y); } }');
  const [submissionId,setSubmissionId]=useState(null), [status,setStatus]=useState(null), [out,setOut]=useState('');
  const [leaderboard,setLeaderboard]=useState([]);
  useEffect(()=>{ const t=setInterval(()=>fetch('/api/proxy/contests/'+id+'/leaderboard').then(r=>r.json()).then(j=>setLeaderboard(j)).catch(()=>{}),15000); return ()=>clearInterval(t); },[id]);
  async function submit(){
    const res = await fetch('/api/proxy/submissions',{method:'POST',headers:{'Content-Type':'application/json'},body:JSON.stringify({username: new URLSearchParams(window.location.search).get('user')||'demo', sourceCode: code})});
    const j = await res.json(); setSubmissionId(j.submissionId); setStatus('PENDING'); poll(j.submissionId);
  }
  async function poll(id){ const r = await fetch('/api/proxy/submissions/'+id); const j = await r.json(); setStatus(j.status); if(j.status && j.status!=='PENDING' && j.status!=='RUNNING') setOut('Score: '+j.score); if(['PENDING','RUNNING'].includes(j.status)) setTimeout(()=>poll(id),2000); }
  return (<div style={{padding:20}}>
    <h1>Contest: {id}</h1>
    <div style={{display:'flex',gap:20}}>
      <div style={{flex:1}}>
        <h2>{problem.title}</h2>
        <pre>{problem.statement}</pre>
        <textarea rows={12} cols={80} value={code} onChange={e=>setCode(e.target.value)} />
        <br/><button onClick={submit}>Submit</button>
        <div>Submission: {submissionId} Status: {status}</div>
        <pre>{out}</pre>
      </div>
      <div style={{width:300}}>
        <h3>Leaderboard (live)</h3>
        <ol>{leaderboard.map((l,i)=>(<li key={i}>{l.username} - {l.solved || 0}</li>))}</ol>
      </div>
    </div>
  </div>);
}
