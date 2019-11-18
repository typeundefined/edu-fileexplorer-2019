<#include "base.ftl">
<#macro page_head>
    <@common_page_head/>
    <script type="text/javascript" src="/scripts/sc.js"></script>
    <title>File Manager</title>
</#macro>
<#macro page_body>
  <nav aria-label="breadcrumb">
    <ol class="breadcrumb">
      <#list directory.parentFolders as folder>
              <li class="breadcrumb-item"><a href="/?path=${folder.path?url}">${folder.name}</a></li>
      </#list>
    </ol>
  </nav>
  <div class="container">
      <div class="input-group mb-3 search">
          <input type="text" id="search" placeholder="File name" class="form-control"
                 aria-label="Text input with segmented dropdown button">
          <div class="input-group-append">
              <button type="button" id="search_action" class="btn btn-outline-secondary">Search Here</button>
              <button type="button" class="btn btn-outline-secondary dropdown-toggle dropdown-toggle-split"
                      data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                  <span class="sr-only">Toggle Dropdown</span>
              </button>
              <div class="dropdown-menu">
                  <a class="dropdown-item" onclick="changeSearch(this)">Search Here</a>
                  <a class="dropdown-item" onclick="changeSearch(this)">Search in children</a>
              </div>
          </div>
      </div>
    <p>
        <button class="btn btn-outline-secondary" type="button" data-toggle="collapse" data-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
            &darr; Upload file
        </button>
    </p>
    <div class="collapse" id="collapseExample">
        <form name="uploadingForm" enctype="multipart/form-data" method="POST">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <p>
                <label class="btn btn-outline-secondary">
                    Browse<input id="fileInput" hidden class="btn btn-primary" type="file" name="uploadingFiles" onchange="updateSize();" multiple>
                </label>
                <span class="badge badge-secondary">selected files: <span id="fileNum" class="badge badge-success">0</span></span>
                <span class="badge badge-secondary">total size: <span id="fileSize" class="badge badge-success">0</span></span>
                <label class="btn btn-outline-secondary">
                    Upload<input type="submit" hidden class="btn btn-outline-secondary" >
                </label>

            </p>
        </form>
        <script>
            function updateSize() {
                var nBytes = 0,
                    oFiles = document.getElementById("fileInput").files,
                    nFiles = oFiles.length;
                for (var nFileId = 0; nFileId < nFiles; nFileId++) {
                    nBytes += oFiles[nFileId].size;
                }
                var sOutput = nBytes + " bytes";
                // optional code for multiples approximation
                for (var aMultiples = ["KiB", "MiB", "GiB", "TiB", "PiB", "EiB", "ZiB", "YiB"], nMultiple = 0, nApprox = nBytes / 1024; nApprox > 1; nApprox /= 1024, nMultiple++) {
                    sOutput = nApprox.toFixed(3) + " " + aMultiples[nMultiple] + " (" + nBytes + " bytes)";
                }
                // end of optional code
                document.getElementById("fileNum").innerHTML = nFiles;
                document.getElementById("fileSize").innerHTML = sOutput;
            }
        </script>
    </div>
    <p>
        <button class="btn btn-outline-secondary" type="button" data-toggle="collapse" data-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
            &darr; Upload file
        </button>
    </p>
    <div class="collapse" id="collapseExample">
        <form name="uploadingForm" enctype="multipart/form-data" method="POST">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <p>
                <label class="btn btn-outline-secondary">
                    Browse<input id="fileInput" hidden class="btn btn-primary" type="file" name="uploadingFiles" onchange="updateSize();" multiple>
                </label>
                <span class="badge badge-secondary">selected files: <span id="fileNum" class="badge badge-success">0</span></span>
                <span class="badge badge-secondary">total size: <span id="fileSize" class="badge badge-success">0</span></span>
                <label class="btn btn-outline-secondary">
                    Upload<input type="submit" hidden class="btn btn-outline-secondary" >
                </label>

            </p>
        </form>
        <script>
            function updateSize() {
                var nBytes = 0,
                    oFiles = document.getElementById("fileInput").files,
                    nFiles = oFiles.length;
                for (var nFileId = 0; nFileId < nFiles; nFileId++) {
                    nBytes += oFiles[nFileId].size;
                }
                var sOutput = nBytes + " bytes";
                // optional code for multiples approximation
                for (var aMultiples = ["KiB", "MiB", "GiB", "TiB", "PiB", "EiB", "ZiB", "YiB"], nMultiple = 0, nApprox = nBytes / 1024; nApprox > 1; nApprox /= 1024, nMultiple++) {
                    sOutput = nApprox.toFixed(3) + " " + aMultiples[nMultiple] + " (" + nBytes + " bytes)";
                }
                // end of optional code
                document.getElementById("fileNum").innerHTML = nFiles;
                document.getElementById("fileSize").innerHTML = sOutput;
            }
        </script>
    </div>

    <div class="row bg-white p-3 border-bottom">
          <div class="col">
              <strong>Name</strong>
          </div>
          <div class="col-6 row">
              <div class="col-8"><strong>Date of change</strong></div>
              <div class="col-4"><strong>Size</strong></div>
          </div>
      </div>
      <div id="files_container" class="bg-white">
          <#import "filesView.ftl" as macro>
          <@macro.show_all directory/>
      </div>
  </div>
</#macro>
<@render_whole_page />
