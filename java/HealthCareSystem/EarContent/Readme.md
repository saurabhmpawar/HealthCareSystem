
following is query for finding distance between two lat long

SELECT *, ( 3959 * acos( cos( radians(18.5204) ) * cos( radians( `latitude` ) ) * cos( radians( `longitude` ) - radians(73.8567) ) + sin( radians(18.5204) ) * sin( radians( `latitude` ) ) ) ) AS distance FROM cashpoints HAVING distance < 25 ORDER BY distance LIMIT 0 , 20

