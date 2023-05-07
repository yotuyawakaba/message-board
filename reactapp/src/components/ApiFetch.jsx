import React, {useState, useEffect} from 'react';
import axios from 'axios';
import Grid from '@mui/material/Grid';
import { CustomizedTables } from "./BodyMain";


export const Content = () => {
    const [post, setPosts] = useState([])

    useEffect(() => {
        axios.get('http://localhost:8080/api/list')
        .then(res => {
            setPosts(res.data)
        })
    }, [])
    // CustomizedTables(post);
    const getContent = getObj => {
        return (
            <div>
			<ul>
				<li>{getObj.content}</li>
				<li>{getObj.created}</li>
				<li>{getObj.updated}</li>
			</ul>
		</div>
            
        );
    };
    return (
        
        <Grid container spacing={2}>
            {post.map(contentObj => getContent(contentObj))}
        </Grid>
    )
}
