<div>
    <p>Are you sure to detele this client? -> {{ $client->name }}</p>
    <button onclick="location.href='{{ url('/client/'.$client->id.'/delete') }}'">Yes</button>
    <button onclick="location.href='{{ url('/clients') }}'">No</button>
</div>
